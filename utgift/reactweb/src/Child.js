import React from "react"
export default class Child extends React.Component {
    delete(id) {
        this.props.delete(id)
    }

    render() {
        console.log("Child", this.props.utgifter)
        return (
            <div>
                {this.props.utgifter.map((el) => (
                    <p onClick={this.delete.bind(this, 1)}>
                        {el.navn}
                        {el.pris}
                        {el.kategori}
                    </p>
                ))}
            </div>
        )
    }
}
