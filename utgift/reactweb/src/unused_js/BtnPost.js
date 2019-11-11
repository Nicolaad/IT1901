import React from "react"
import Btn from "./Btn"

export default class BtnPost extends React.Component {
    constructor(props) {
        super(props)

        this.post = this.post.bind(this)
        this.state = {
            value: null
        }
    }

    render() {
        return <Btn value="post" onClick={this.post}></Btn>
    }
    post() {
        let txt1 = document.getElementById("txt1").value
        let txt2 = document.getElementById("txt2").value
        let txt3 = document.getElementById("txt3").value
        fetch("http://localhost:8080/utgiftlist", {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json"
            },
            mode: "cors",
            body: JSON.stringify([{ navn: txt1, pris: txt2, kategori: txt3 }])
        })
    }
}
