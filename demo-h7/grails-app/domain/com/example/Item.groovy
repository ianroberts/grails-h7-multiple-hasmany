package com.example

/**
 * Version of the Item class with two unidirectional hasMany associations.
 * This version persists correctly in Hibernate 5 but not 7.
 */
class Item {
  String name

  static hasMany = [roles: Role, tags: Tag]
}
