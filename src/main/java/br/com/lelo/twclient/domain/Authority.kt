package br.com.lelo.twclient.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Size

@Entity
@Table(name = "AUTHORITY")
class Authority {

    @Id
    @Size(min = 0, max = 50)
    var name: String = ""
}