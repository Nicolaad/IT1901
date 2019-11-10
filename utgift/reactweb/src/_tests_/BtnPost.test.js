import React from "react"
import { unmountComponentAtNode, render} from "react-dom"
import BtnPost from "../BtnPost.js";
import {act, findRenderedComponentWithType} from "react-dom/test-utils"

import { shallow, mount} from 'enzyme';

import { configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import App from "../App";
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

describe("testing btnPost.js", () => {
    it("BtnPost renders", () =>{
        act(() => {
            render(<BtnPost />, container)
        })
        expect(container).toBeTruthy()
    })
})