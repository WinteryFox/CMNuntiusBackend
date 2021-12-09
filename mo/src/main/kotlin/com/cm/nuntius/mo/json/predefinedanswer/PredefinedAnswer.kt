package com.cm.nuntius.mo.json.predefinedanswer

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table

@Table("predefined_answer")
data class PredefinedAnswer(
    @Id
    val id: String,
    val text: String,
    val category: String,
    @Version
    val version: Short
)