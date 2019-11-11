export default class Client {
    numbers() {
        let result = new Promise((resolve, reject) => {
            let request = new XMLHttpRequest()
            // await fetch("http://localhost:8080/utgiftlist").then(d => d.body.json()).then(data => )
            request.open("GET", "http://localhost:8080/utgiftlist")
            request.onreadystatechange = () => {
                let raw = request.responseText
                if (raw !== "") {
                    console.log(raw)
                    let objectified = JSON.parse(raw)
                    resolve(objectified)
                }
            }
            request.send()
        })
        return result
    }
}
