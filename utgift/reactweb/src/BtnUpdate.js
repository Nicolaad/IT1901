import React from "react";
import Btn from "./Btn";

export default class BtnUpdate extends React.Component {
    state = { users: [] };
    update() {
        fetch("/utgiftlist")
            .then(res => res.json())
            .then(users => this.setState({ users }));
    }

    render() {
        return <Btn value="update" onClick={this.update}></Btn>;
    }
}
