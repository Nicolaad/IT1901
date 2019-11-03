import React from "react";
import Client from "./Client";

export default class Numbers extends React.Component {
    state = { users: [] };

    constructor(props) {
        super(props);
        this.client = new Client();
        this.state = { numbers: [] };
        this.client.numbers().then(r => this.setState({ numbers: r }));
    }
    render() {
        try {
            return (
                <section>
                    {this.state.numbers.map(n => (
                        <div>
                            {" "}
                            {n.navn} {n.pris} {n.kategori}
                        </div>
                    ))}
                </section>
            );
        } catch (error) {}
    }
}
