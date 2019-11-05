import React from "react"
import { arrowFunctionExpression } from "@babel/types"
import { ResponsiveContainer, PieChart, Tooltip, Pie, Legend, Label } from "recharts"

export default class PieChartUtgifter extends React.Component {
    render() {
        let data01 = []
        console.log(this.props)
        try {
            console.log(this.props.utgifter.length)
            for (let i = 0; i < this.props.utgifter.length; i++) {
                data01.push({
                    name: this.props.utgifter[i].navn,
                    value: this.props.utgifter[i].pris
                })
            }
        } catch {
            console.log("heisann")
        }

        try {
            return (
                <div className ="PieChart">
                    <PieChart width={400} height={400}>
                        <Pie
                            className ="PieChart"
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
            )
        } catch {
            return <p> heii</p>
        }
    }
}
