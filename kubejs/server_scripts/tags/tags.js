function indrev_tag_unify(event) {
  let material = (materialId) => {
    event.add("c:ingots/" + materialId, "indrev:" + materialId + "_ingot")
    event.add("c:nuggets/" + materialId, "indrev:" + materialId + "_nugget")
    event.add("c:plates/" + materialId, "indrev:" + materialId + "_plate")
    event.add("c:dusts/" + materialId, "indrev:" + materialId + "_dust")
    event.add("c:storage_blocks/" + materialId, "indrev:" + materialId + "_block")
  }
  material("lead")
  material("bronze")
  material("electrum")
  material("steel")
  material("silver")
  material("tin")

  event.removeAll("c:storage_blocks/steel")
  event.add("c:storage_blocks/steel", AR("steel_block"))
  event.removeAll("c:nuggets/steel")
  event.add("c:nuggets/steel", AR("steel_nugget"))

  let material_vanilla = (materialId) => {
    event.add("c:dusts/" + materialId, "indrev:" + materialId + "_dust")
  }
  material_vanilla("iron")
  material_vanilla("gold")
  material_vanilla("copper")
  material_vanilla("diamond")
  material_vanilla("copper")
}

function stripped_wood_tag_unify(event) {
  missing_wood_types_stripped.forEach(e => {
    let log = asIdentifier("stripped_" + e[1] + "_log", e[0])
    let wood = asIdentifier("stripped_" + e[1] + "_wood", e[0])

    if (e[0] == "malum") {
      wood = asIdentifier("stripped_" + e[1], e[0])
    }

    if (!listIncludes(wood_types_no_wood, e)) {
      event.add("c:stripped_wood", wood)
    }

    event.add("c:stripped_logs", log)
  })
}

function ad_astra_tag_unify(event) {
  let material = (id) => {
    event.add("c:ingots/" + id, "ad_astra:" + id + "_ingot")
    event.add("c:nuggets/" + id, "ad_astra:" + id + "_nugget")
    event.add("c:storage_blocks/" + id, "ad_astra:" + id + "_block")
  }
  material("desh")
  material("ostrum")
  material("calorite")
}

onEvent("tags.items", event => {
  indrev_tag_unify(event)
  ad_astra_tag_unify(event)
  stripped_wood_tag_unify(event)
  
  event.get("create:upright_on_belt")
		.add(AE2("red_paint_ball"))
		.add(AE2("yellow_paint_ball"))
		.add(AE2("green_paint_ball"))
		.add(AE2("blue_paint_ball"))
		.add(AE2("magenta_paint_ball"))
		.add(AE2("black_paint_ball"))
})