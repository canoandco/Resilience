package com.resilience.security


import com.resilience.Media
import com.resilience.MediaCategory

import javax.xml.bind.annotation.XmlTransient

@XmlTransient
class User {

	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static transients = ['springSecurityService']

    static hasMany = [subscriptions:MediaCategory]

	static constraints = {
		username blank: false, nullable:false, unique: true
		password blank: false, nullable:false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
