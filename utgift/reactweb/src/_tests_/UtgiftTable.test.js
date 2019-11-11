import React from "react"
import { unmountComponentAtNode, render} from "react-dom"
import UtgiftTable from "../UtgiftTable";
import {act} from "react-dom/test-utils"
import { configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

//npm test -- --coverage --watchAll=false for dekningsgrad
configure({ adapter: new Adapter() });

var container = null
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
        navn: "Laks",
        pris: "10",
        kategori: "mat",
        id: 100
    }
]


describe("UtgiftTableTest", () => {

    it("teste rendring uten noe innhold", () => {
        act(() => {
            render(< UtgiftTable/>, container)
        })
        expect(container.textContent).toBe("kunne ikke rendre");
    })

    it("teste rendring med noe innhold", () => {
        act(() => {
            render(< UtgiftTable utgifter={fakeUtgift}/>, container)
        })
        expect(container.textContent).toBe("NavnPrisKategoriSlettLaks10matSlett");

    })


})

