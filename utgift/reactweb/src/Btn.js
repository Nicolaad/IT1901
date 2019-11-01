<<<<<<< HEAD
import React from "react"
import App from "./App"

export default class Button extends React.Component {
    constructor(props) {
        super(props)
        this.onClick = props.onClick
        this.value = props.value
=======
import React from "react";
import App from "./App";

export default class Button extends React.Component {
    constructor(props) {
        super(props);
        this.onClick = props.onClick;
        this.value = props.value;
>>>>>>> master
    }

    post() {
        fetch("http://localhost:8080/utgiftlist", {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json"
            },
            mode: "cors",
<<<<<<< HEAD
            body: JSON.stringify([{ navn: "hei", pris: "200", kategori: "feis" }])
        })
=======
            body: JSON.stringify([
                { navn: "hei", pris: "200", kategori: "feis" }
            ])
        });
>>>>>>> master
    }

    render() {
        return (
            <button id="btnPost" onClick={this.onClick}>
                {this.value}
            </button>
<<<<<<< HEAD
        )
=======
        );
>>>>>>> master
    }
}
