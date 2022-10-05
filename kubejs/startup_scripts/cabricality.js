let modpackId = "cabricality"

let DEBUG = false

let MOD = (domain, id, x) => (x ? `${x}x ` : "") + (id.startsWith("#") ? "#" : "") + domain + ":" + id.replace("#", "")
let CR = (id, x) => MOD("create", id, x)
let MC = (id, x) => MOD("minecraft", id, x)
let CABF = (id, x) => MOD(modpackId, id, x)
let C = (id, x) => MOD("c", id, x)
let F = (id, x) => MOD("fabric", id, x)
let IV = (id, x) => MOD("indrev", id, x)
let AE2 = (id, x) => MOD("ae2", id, x)
let KB = (id, x) => MOD("kibe", id, x)
let CX = (id, x) => MOD("coxinhautilities", id, x)
let PMD = (id, x) => MOD("promenade", id, x)
let AR = (id, x) => MOD("ad_astra", id, x)
let AP = (id, x) => MOD("architects_palette", id, x)
let FD = (id, x) => MOD("farmersdelight", id, x)
let ED = (id, x) => MOD("extended_drawers", id, x)
let BC = (id, x) => MOD("bitsandchisels", id, x)
let TC = (id, x) => MOD("tconstruct", id, x)
let CC = (id, x) => MOD("computercraft", id, x)
let KJ = (id, x) => MOD("kubejs", id, x)
let ML = (id, x) => MOD("malum", id, x)
let TR = (id, x) => MOD("terrestria", id, x)

function asIdentifier(path, namespace) {
    if (namespace) {
        return namespace + ":" + path
    } else {
        return modpackId + ":" + path
    }
}