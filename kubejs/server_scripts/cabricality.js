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
let ML = (id, x) => MOD("malum", id, x)

const Minecraft = java('net.minecraft.client.Minecraft')
const coinCoolingConst = 41
const diceCoolingConst = 47

var randomEventCooling = []

function asIdentifier(path, namespace) {
	if (namespace) {
		return namespace + ":" + path
	} else {
		return modpackId + ":" + path
	}
}

function listIncludes(full, sub) {
	let returnV = false
	full.forEach(e => {
		if (e.toString() == sub.toString()) {
			returnV = true
		}
	})
	return returnV
}

let wood_types = [
	MC("oak"), MC("spruce"), MC("birch"), MC("jungle"), MC("acacia"), MC("dark_oak"), MC("crimson"), MC("warped"),
	PMD("dark_amaranth"), PMD("palm"), PMD("cherry_oak"),
	AP("twisted"),
	TC("bloodshroom"), TC("skyroot"), TC("greenheart")
]

let missing_wood_types_normal = [
	["malum", "runewood"],
	["malum", "soulwood"],

	["terrestria", "rainbow_eucalyptus"],
	["terrestria", "cypress"],
	["terrestria", "hemlock"],
	["terrestria", "redwood"],
	["terrestria", "yucca_palm"],
	["terrestria", "sakura"],
	["terrestria", "japanese_maple"],
	["terrestria", "willow"],
	["terrestria", "rubber"],

	["ad_astra", "glacian"]
]

let missing_wood_types_stripped = [
	["malum", "runewood"],
	["malum", "soulwood"],

	["terrestria", "rainbow_eucalyptus"],
	["terrestria", "cypress"],
	["terrestria", "hemlock"],
	["terrestria", "redwood"],
	["terrestria", "yucca_palm"],
	["terrestria", "sakura"],
	["terrestria", "japanese_maple"],
	["terrestria", "willow"],
	["terrestria", "rubber"],

	["promenade", "palm"],
	["promenade", "cherry_oak"],
	["architects_palette", "twisted"],

	["tconstruct", "bloodshroom"],
	["tconstruct", "skyroot"],
	["tconstruct", "greenheart"]
]

let wood_types_no_wood = [
	["ad_astra", "glacian"],

	["terrestria", "yucca_palm"],
	["terrestria", "sakura"]
]

function dimensionalCommanding(server, dimension, command) {
	server.runCommandSilent("execute in " + dimension.toString() + " run " + command)
}