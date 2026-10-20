package com.example

import grails.gorm.transactions.Transactional

class BootStrap {
    def init = {
      createItem(Item)
      createItem(UntaggedItem)
    }

    @Transactional
    void createItem(Class itemClazz) {
      log.info("======== Creating ${itemClazz.getSimpleName()} =========")
      def item = itemClazz.newInstance(name: "example")
      item.save(flush: true)
      log.info("Created ${item}")

      log.info("findOrCreate example role 1")
      def role = Role.findOrCreateByAuthority("ROLE_EXAMPLE")
      log.info("Add role to item")
      item.addToRoles(role)

      log.info("findOrCreate example role 2")
      def role2 = Role.findOrCreateByAuthority("ROLE_EXAMPLE2")
      log.info("Add role to item")
      item.addToRoles(role2)

      log.info("Save and flush")
      item.save(flush: true)

      log.info("Remove role 1")
      item.removeFromRoles(role)

      log.info("Save and flush")
      item.save(flush: true)

      log.info("Reload")
      item.refresh()
      log.info("Item has roles ${item.roles*.authority}")
    }

    def destroy = {
    }

}
