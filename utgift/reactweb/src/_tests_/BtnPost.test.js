import React from "react"
import { unmountComponentAtNode, render } from "react-dom"
import BtnPost from "../unused_js/BtnPost.js"
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

describe("testing btnPost.js", () => {
    it("BtnPost renders", () => {
        act(() => {
            render(<BtnPost />, container)
        })
        expect(container).toBeTruthy()
    })
})
