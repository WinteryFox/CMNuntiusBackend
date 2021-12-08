package com.cm.nuntius.mo.repository.predefinedanswer

import com.cm.nuntius.mo.json.predefinedanswer.PredefinedAnswerCategory
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PredefinedAnswerCategoryRepository : CoroutineCrudRepository<PredefinedAnswerCategory, String>
