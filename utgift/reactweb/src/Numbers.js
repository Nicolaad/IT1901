<<<<<<< HEAD
import React from "react"
import Client from "./Client"

export default class Numbers extends React.Component {
    state = { users: [] }

    constructor(props) {
        super(props)
        this.client = new Client()
        this.state = { numbers: [] }
        this.client.numbers().then((r) => this.setState({ numbers: r }))
=======
import React from "react";
import Client from "./Client";

export default class Numbers extends React.Component {
    state = { users: [] };

    constructor(props) {
        super(props);
        this.client = new Client();
        this.state = { numbers: [] };
        this.client.numbers().then(r => this.setState({ numbers: r }));
>>>>>>> master
    }
    render() {
        try {
            return (
                <section>
<<<<<<< HEAD
                    {this.state.numbers.map((n) => (
=======
                    {this.state.numbers.map(n => (
>>>>>>> master
                        <div>
                            {" "}
                            {n.navn} {n.pris} {n.kategori}
                        </div>
                    ))}
                </section>
<<<<<<< HEAD
            )
=======
            );
>>>>>>> master
        } catch (error) {}
    }
}
