package com.cm.nuntius.mo.json.predefinedanswer

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table

@Table("predefined_answer_category")
data class PredefinedAnswerCategory(
    @Id
    val id: String,
    @Version
    val version: Short
)
