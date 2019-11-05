import React from "react"
export default class UtgiftTable extends React.Component {

    constructor(props) {
        super(props)
        this.renderlist = this.renderlist.bind(this)
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
                    <li key={utgift.id}>
                        {utgift.navn} <p className="listText"> </p>
                        {utgift.pris} <p className="listText"> </p>
                        {utgift.kategori} <p className="listText"> </p>
                        <button
                            className="DeleteButton"
                            onClick={this.delete.bind(this, utgift.id)}
                        >
                            Slett
                        </button>
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
