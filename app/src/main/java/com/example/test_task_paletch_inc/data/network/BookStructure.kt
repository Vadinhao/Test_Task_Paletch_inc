package com.example.test_task_paletch_inc.data.network

import com.squareup.moshi.Json

data class Book(
    @Json(name = "age_group") val ageGroup: String?,
    @Json(name = "amazon_product_url") val amazonProductUrl: String,
    @Json(name = "article_chapter_link") val articleChapterLink: String?,
    @Json(name = "author") val author: String,
    @Json(name = "book_image") val bookImage: String,
    @Json(name = "book_image_width") val bookImageWidth: Int?,
    @Json(name = "book_image_height") val bookImageHeight: Int?,
    @Json(name = "book_review_link") val bookReviewLink: String?,
    @Json(name = "book_uri") val bookUri: String,
    @Json(name = "contributor") val contributor: String,
    @Json(name = "contributor_note") val contributorNote: String?,
    @Json(name = "created_date") val createdDate: String,
    @Json(name = "description") val description: String,
    @Json(name = "first_chapter_link") val firstChapterLink: String?,
    @Json(name = "price") val price: String,
    @Json(name = "primary_isbn10") val primaryIsbn10: String,
    @Json(name = "primary_isbn13") val primaryIsbn13: String,
    @Json(name = "publisher") val publisher: String,
    @Json(name = "rank") val rank: Int,
    @Json(name = "rank_last_week") val rankLastWeek: Int,
    @Json(name = "sunday_review_link") val sundayReviewLink: String?,
    @Json(name = "title") val title: String,
    @Json(name = "updated_date") val updatedDate: String,
    @Json(name = "weeks_on_list") val weeksOnList: Int,
    @Json(name = "buy_links") val buyLinks: List<BuyLink>
)

data class BuyLink(
    @Json(name = "name") val name: String,
    @Json(name = "url") val url: String
)

data class ListCategoryWithBooks(
    @Json(name = "status") val status: String,
    @Json(name = "copyright") val copyright: String,
    @Json(name = "num_results") val numResults: Int,
    @Json(name = "results") val results: ListCategoryResult
)

data class ListCategoryResult(
    @Json(name = "bestsellers_date") val bestsellersDate: String,
    @Json(name = "published_date") val publishedDate: String,
    @Json(name = "published_date_description") val publishedDateDescription: String,
    @Json(name = "previous_published_date") val previousPublishedDate: String,
    @Json(name = "next_published_date") val nextPublishedDate: String?,
    @Json(name = "lists") val lists: List<BookList>
)

data class BookList(
    @Json(name = "list_id") val listId: Int,
    @Json(name = "list_name") val listName: String,
    @Json(name = "list_name_encoded") val listNameEncoded: String,
    @Json(name = "display_name") val displayName: String,
    @Json(name = "updated") val updated: String,
    @Json(name = "list_image") val listImage: String?,
    @Json(name = "list_image_width") val listImageWidth: Int?,
    @Json(name = "list_image_height") val listImageHeight: Int?,
    @Json(name = "books") val books: List<Book>
)