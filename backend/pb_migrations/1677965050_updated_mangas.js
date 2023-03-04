migrate((db) => {
  const dao = new Dao(db)
  const collection = dao.findCollectionByNameOrId("bhjwaxxaem0u430")

  // remove
  collection.schema.removeField("6zuafgwo")

  // add
  collection.schema.addField(new SchemaField({
    "system": false,
    "id": "lytv0tq6",
    "name": "nextVolumeRelease",
    "type": "text",
    "required": false,
    "unique": false,
    "options": {
      "min": null,
      "max": null,
      "pattern": ""
    }
  }))

  return dao.saveCollection(collection)
}, (db) => {
  const dao = new Dao(db)
  const collection = dao.findCollectionByNameOrId("bhjwaxxaem0u430")

  // add
  collection.schema.addField(new SchemaField({
    "system": false,
    "id": "6zuafgwo",
    "name": "nextVolumeRelease",
    "type": "date",
    "required": false,
    "unique": false,
    "options": {
      "min": "",
      "max": ""
    }
  }))

  // remove
  collection.schema.removeField("lytv0tq6")

  return dao.saveCollection(collection)
})
