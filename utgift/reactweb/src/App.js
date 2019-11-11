import React from "react"
import "./css/App.css"
import UtgiftTable from "./UtgiftTable"
import PieChartUtgifter from "./PieChartUtgifter"

export default class App extends React.Component {
    constructor(props) {
        super(props)
        this.state = { utgifter: [] }
        this.update = this.update.bind(this)
        this.post = this.post.bind(this)
        this.delete = this.delete.bind(this)
        this.deleteAtServer = this.deleteAtServer.bind(this)
        this.hasContent = this.hasContent.bind(this)
        this.clearTextField = this.clearTextField.bind(this)
    }
    //finn siste id.
    componentDidMount() {
        this.update()
    }
    async update() {
        await fetch("/utgiftlist")
            .then((res) => res.json())
            .then((utgifter) => this.setState({ utgifter }))
    }

    async post() {
        let txt1 = document.getElementById("txt1").value
        let txt2 = document.getElementById("txt2").value
        let txt3 = document.getElementById("txt3").value
        if (this.hasContent(txt1) && this.hasContent(txt3) && this.isNumeric(txt2)) {
            await fetch("http://localhost:8080/utgiftlist", {
                method: "POST",
                headers: {
                    Accept: "application/json",
                    "Content-Type": "application/json"
                },

                body: JSON.stringify([
                    { navn: txt1, pris: txt2, kategori: txt3, id: 100 }
                ])
            })

            this.clearTextField()
            this.update()
        }
    }

    hasContent(text) {
        return text.length >= 1
    }

    isNumeric(num) {
        return !isNaN(num) && num > 0
    }

    clearTextField() {
        document.getElementById("txt1").value = ""
        document.getElementById("txt2").value = ""
        document.getElementById("txt3").value = ""
    }

    async deleteAtServer(id) {
        await fetch("http://localhost:8080/utgiftlist/" + id, {
            method: "DELETE",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json"
            }
            // body: JSON.stringify([{ pris: 2000 }])
        })
    }

    delete(id) {
        console.log(id)
        this.setState((prevState) => ({
            utgifter: prevState.utgifter.filter((el) => el.id !== id)
        }))
        console.log(this.state.utgifter)
        this.deleteAtServer(id)
    }

    render() {
        return (
            <div className="App">
                <h1> Utgifter</h1>

                <UtgiftTable utgifter={this.state.utgifter} delete={this.delete} />
                <PieChartUtgifter utgifter={this.state.utgifter} />
                <div className="push"></div>
                <div className="labels">
                    <label>Navn: </label>
                    <input type="text" id="txt1" />
                    <label>Pris: </label>
                    <input type="text" id="txt2" />
                    <label>Kategori: </label>
                    <input type="text" id="txt3" />
                    <button className="PostButton" onClick={this.post}>
                        Legg til Utgift
                    </button>
                </div>
            </div>
        )
    }
}
