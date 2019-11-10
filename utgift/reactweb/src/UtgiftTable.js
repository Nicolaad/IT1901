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
            <table id="listview">
                <thead>
                    <tr id="tHead">
                        <th>Navn</th>
                        <th>Pris</th>
                        <th>Kategori</th>
                        <th>Slett</th>
                    </tr>
                </thead>
                <tbody>
                    {this.props.utgifter.map((utgift) => (
                        <tr key={utgift.id}>
                            <td>{utgift.navn}</td>
                            <td>{utgift.pris}</td>
                            <td>{utgift.kategori}</td>
                            <td>
                                <button
                                    className="DeleteButton"
                                    onClick={this.delete.bind(this, utgift.id)}
                                >
                                    Slett
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        )
    }

    render() {
        try {
            return this.renderlist()
        } catch {
            return <p>kunne ikke rendre</p>
        }
    }
}
