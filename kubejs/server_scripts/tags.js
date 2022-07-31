function indrevUnify(event) {
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

  let materialVanilla = (materialId) => {
    event.add("c:dusts/" + materialId, "indrev:" + materialId + "_dust")
  }
  materialVanilla("iron")
  materialVanilla("gold")
  materialVanilla("copper")
  materialVanilla("diamond")
  materialVanilla("copper")
}
onEvent('tags.items', event => {
  indrevUnify(event)
})