package com.bpc.journalerapi.data

import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.rmi.registry.LocateRegistry
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "note")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NamedQuery(name = "Note.findByTitle", query = "SELECT n FROM Note n WHERE n.title LIKE ?1")
data class Note(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(36)")
    var id: String = "",
    var title: String,
    var message: String,
    var location: String = "",
    @CreationTimestamp
    var created: Date = Date(),
    @UpdateTimestamp
    var modified: Date = Date()
) {

    /**
     * Hibernate tries creates a bean via reflection.
     * It does the object creation by calling the no-arg constructor.
     * Then it uses the setter methods to set the properties.
     *
     * If there is no default constructor, the following exception happens:
     * org.hibernate.InstantiationException: No default constructor for entity...
     */
    constructor() : this(
        "", "", "", ""
    )

}
