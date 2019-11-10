import React from "react"

import App from "../App"
import { unmountComponentAtNode, render } from "react-dom"
import {shallow} from "enzyme";
import { act } from "react-dom/test-utils"
import UtgiftTable from "../UtgiftTable";
import { configure, mount } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
configure({ adapter: new Adapter() });

let container = null
beforeEach(() => {
    // setup a DOM element as a render target
    container = document.createElement("div")
    document.body.appendChild(container)
})

afterEach(() => {
    // cleanup on exiting
    unmountComponentAtNode(container)
    container.remove()
    container = null
})

const fakeUtgift = [
    {
        navn: "Fisk",
        pris: "32",
        kategori: "mat",
        id: 100
    }
]

describe("Application tests", () => {

    it("burde ikke være udefinert", () => {
        act(() => {
            render(<App />, container)
        })
        expect(container).toBeTruthy()
    })
    it("renders utgift data ", async () => {

        jest.spyOn(global, "fetch").mockImplementation(() =>
            Promise.resolve({
                json: () => Promise.resolve(fakeUtgift)
            })
        )

        await act(async () => {
            render(<App />, container)
            console.log(container.getBy)
        })

        expect(container.textContent).toBe(
            " UtgifterNavnPrisKategoriSlettFisk32matSlettNavn: Pris: Kategori: Legg til Utgift"
        )
        //expect(container.querySelector("").textContent).toBe(fakeUtgift.navn);
        global.fetch.mockRestore()
    })
})
    describe("Tester funksjonene" , () => {
    it("tester content funksjonen", () => {
        const wrapper = shallow(<App/>);
        const instance = wrapper.instance();
        expect(instance.hasContent("yes")).toBe(true)
        expect(instance.hasContent("")).toBe(false)
    })
    it("tester is numeric funksjonen", () =>{
        const wrapper = shallow(<App/>);
        const instance = wrapper.instance();
        expect(instance.isNumeric(1)).toBe(true)
        expect(instance.isNumeric("usant")).toBe(false)
    })
    /*it("Tester ClearTextField funksjonen", () => {
        const wrapper = mount(<App  utgifter={fakeUtgift} />);
        wrapper.setState[{utgifter : fakeUtgift}]
        const instance = wrapper.instance();
        instance.update()
        expect(wrapper.text()).toBe(" UtgifterNavnPrisKategoriSlettFisk32matSlettNavn: Pris: Kategori: Legg til Utgift")

        //expect(instance.state.utgifter).toStrictEqual(fakeUtgift)
        //expect(instance.state.utgifter).toStrictEqual([])
    })*/
})

