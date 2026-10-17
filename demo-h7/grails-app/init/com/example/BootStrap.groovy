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

      log.info("findOrCreate example role")
      def role = Role.findOrCreateByAuthority("ROLE_EXAMPLE")
      log.info("Add role to item")
      item.addToRoles(role)

      log.info("Save and flush")
      item.save(flush: true)
    }

    def destroy = {
    }

}
