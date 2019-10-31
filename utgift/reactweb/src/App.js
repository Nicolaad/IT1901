import React from "react";
import "./App.css";
import UtgiftTable from "./UtgiftTable";

//import PieChart from "react-minimal-pie-chart";
import {
    ResponsiveContainer,
    PieChart,
    Tooltip,
    Pie,
    Legend,
    Label
} from "recharts";

export default class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = { users: [], loser: true };
        this.update = this.update.bind(this);
        this.post = this.post.bind(this);
        this.fisk = this.fisk.bind(this);
    }

    componentDidMount() {
        this.update();
    }
    async update() {
        const response = await fetch("/utgiftlist")
            .then(res => res.json())
            .then(users => this.setState({ users }));
    }

    async post() {
        let txt1 = document.getElementById("txt1").value;
        let txt2 = document.getElementById("txt2").value;
        let txt3 = document.getElementById("txt3").value;
        await fetch("http://localhost:8080/utgiftlist", {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json"
            },
            mode: "cors",
            body: JSON.stringify([{ navn: txt1, pris: txt2, kategori: txt3 }])
        });
        this.update();
    }

    fisk() {
        return <label>fisk</label>;
    }

    render() {
        const data01 = [
            { name: "Group A", value: 2400 },
            { name: "Group B", value: 4567 },
            { name: "Group C", value: 1398 },
            { name: "Group D", value: 9800 },
            { name: "Group E", value: 3908 },
            { name: "Group F", value: 4800 }
        ];
        return (
            <div className="App">
                <h1> Utgifter</h1>
                <UtgiftTable users={this.state.users} />
                <label>Navn: </label>
                <input type="text" id="txt1" />
                <label>Pris: </label>
                <input type="text" id="txt2" />
                <label>Kategori: </label>
                <input type="text" id="txt3" />
                <button onClick={this.post}>Post</button>

                <PieChart width={400} height={400}>
                    <Pie
                        isAnimationActive={false}
                        data={data01}
                        cx={200}
                        cy={200}
                        outerRadius={80}
                        fill="#8884d8"
                        label
                    />
                    <Tooltip />
                </PieChart>

                <Tooltip />
            </div>
        );
    }
}

//   <button onClick={this.update}>Update</button>
