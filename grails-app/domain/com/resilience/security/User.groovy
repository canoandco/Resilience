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

    static hasMany = ["subscriptions":MediaCategory,"medias":Media]

	static constraints = {
		username blank: false, nullable:false, unique: true
		password blank: false, nullable:false
	}

	static mapping = {
		password column: '`password`'
	}

    @XmlTransient
    def getPassword() {
        return password
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
