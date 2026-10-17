package com.example

/**
 * Version of the Item class with just one unidirectional hasMany.  This
 * version persists correctly in both Hibernate 5 and 7.
 */
class UntaggedItem {
  String name

  static hasMany = [roles: Role]
}
