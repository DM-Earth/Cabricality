function process(name) {
    let split = name.toLowerCase().split('x')
    let cid = ""
    let amount = ""
    split.forEach((e, idx) => {
        if (idx == 0) amount = e;
        else {
            if (idx > 1) cid = cid + "x";
            cid = cid + e.replace("'", "").replace("#", "").replace(' ', '');
        }
    })
    let arr = [cid, amount]
    return arr
}


onEvent("recipes", event => {
    // event.remove({ type: IV("infuse") })
    let professions = []
    global.transactions.forEach(trade => {
        let card = trade[1]

        let inArr = process(trade[0].in)
        let inItem = inArr[0]
        let inItemCount = inArr[1]
        let outArr = process(trade[0].out)
        let outItem = outArr[0]
        let outItemCount = outArr[1]

        /*
        event.shapeless("2x " + card, [card])
        event.custom({
            "type": "indrev:infuse",
            "ingredients": [
                {
                    "item": inItem,
                    "count": inItemCount
                },
                {
                    "item": card,
                    "count": 0
                }
            ],
            "output": {
                "item": outItem,
                "count": outItemCount
            },
            "processTime": 125
        })
        */
    })
})