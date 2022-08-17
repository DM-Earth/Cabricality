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

  let material_vanilla = (materialId) => {
    event.add("c:dusts/" + materialId, "indrev:" + materialId + "_dust")
  }
  material_vanilla("iron")
  material_vanilla("gold")
  material_vanilla("copper")
  material_vanilla("diamond")
  material_vanilla("copper")
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
onEvent('tags.items', event => {
  indrev_tag_unify(event)
  ad_astra_tag_unify(event)
  
  event.get("create:upright_on_belt")
		.add(AE2("red_paint_ball"))
		.add(AE2("yellow_paint_ball"))
		.add(AE2("green_paint_ball"))
		.add(AE2("blue_paint_ball"))
		.add(AE2("magenta_paint_ball"))
		.add(AE2("black_paint_ball"))
})