import React from "react"
import App from "./App"

export default class Button extends React.Component {
    constructor(props) {
        super(props)
        this.onClick = props.onClick
        this.value = props.value
    }

    post() {
        fetch("http://localhost:8080/utgiftlist", {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json"
            },
            mode: "cors",
            body: JSON.stringify([{ navn: "Melk", pris: "20", kategori: "Mat" }])
        })
    }

    render() {
        return (
            <button id="btnPost" onClick={this.onClick}>
                {this.value}
            </button>
        )
    }
}
