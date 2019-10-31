import React from "react";
import { arrowFunctionExpression } from "@babel/types";
export default class UtgiftTable extends React.Component {
    constructor(props) {
        super(props);
        this.renderlist = this.renderlist.bind(this);
    }

    renderlist() {
        console.log(this.props.users);
        return (
            <ul id="listview">
                {this.props.users.map(user => (
                    <li key={user.pris}>
                        {user.navn}
                        {user.pris}
                        {user.kategori}
                    </li>
                ))}
            </ul>
        );
    }
    render() {
        try {
            return this.renderlist();
        } catch {
            return <p> heii</p>;
        }
    }
}
