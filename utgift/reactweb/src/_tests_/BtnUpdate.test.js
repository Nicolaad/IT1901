import React from "react"
import { unmountComponentAtNode, render } from "react-dom"
import BtnUpdate from "../unused_js/BtnUpdate.js"
import { act } from "react-dom/test-utils"

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

describe("testing BtnUpdate.js", () => {
    it("BtnUpdate renders", () => {
        act(() => {
            render(<BtnUpdate />, container)
        })
        expect(container).toBeTruthy()
    })
})
