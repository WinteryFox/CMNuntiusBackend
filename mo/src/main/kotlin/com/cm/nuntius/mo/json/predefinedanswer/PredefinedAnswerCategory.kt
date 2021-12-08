package com.cm.nuntius.mo.json.predefinedanswer

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class PredefinedAnswerCategory (
    @Id
    val id: String,
    val name: String
)
