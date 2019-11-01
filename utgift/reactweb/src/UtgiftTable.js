import React from "react"
import { arrowFunctionExpression } from "@babel/types"
export default class UtgiftTable extends React.Component {
    constructor(props) {
        super(props)
        this.renderlist = this.renderlist.bind(this)
    }
    delete(id) {
        console.log(id)
        this.props.delete(id)
    }
    delete(id) {
        console.log(id)
        this.props.delete(id)
    }

    renderlist() {
        console.log(this.props.utgifter)
        return (
            <ul id="listview">
                {this.props.utgifter.map((utgift) => (
                    <li key={utgift.id} onClick={this.delete.bind(this, utgift.id)}>
                        {utgift.navn}
                        {utgift.pris}
                        {utgift.kategori}
                    </li>
                ))}
            </ul>
        )
    }
    render() {
        try {
            return this.renderlist()
        } catch {
            return <p> heii</p>
        }
    }
}
