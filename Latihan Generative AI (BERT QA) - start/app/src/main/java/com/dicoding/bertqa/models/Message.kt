package com.dicoding.bertqa.models

// data class untuk membedakan pertanyaan dari pengguna dan jawaban dari aplikasi
data class Message(
    val text: String,
    val isFromUser: Boolean
)