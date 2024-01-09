package com.example.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Integer bookId;
    private String bookName;
    private String bookAuthor;
    private String bookVersion;
    private String bookPublishHouse;
    private String bookGrade;
    private Integer bookDownloadNum;
    private Integer bookClickNum;
    private String bookUploader;
    private Integer bookPoints;
    private String bookProfile;
    private String bookCoverUrl;
    private String bookCoverUuid;
    private String bookFileUuid;
    private String categoryName;
    private String categoryAlias;
    private LocalDate createTime;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookVersion() {
        return bookVersion;
    }

    public void setBookVersion(String bookVersion) {
        this.bookVersion = bookVersion;
    }

    public String getBookPublishHouse() {
        return bookPublishHouse;
    }

    public void setBookPublishHouse(String bookPublishHouse) {
        this.bookPublishHouse = bookPublishHouse;
    }

    public String getBookGrade() {
        return bookGrade;
    }

    public void setBookGrade(String bookGrade) {
        this.bookGrade = bookGrade;
    }

    public Integer getBookDownloadNum() {
        return bookDownloadNum;
    }

    public void setBookDownloadNum(Integer bookDownloadNum) {
        this.bookDownloadNum = bookDownloadNum;
    }

    public Integer getBookClickNum() {
        return bookClickNum;
    }

    public void setBookClickNum(Integer bookClickNum) {
        this.bookClickNum = bookClickNum;
    }

    public String getBookUploader() {
        return bookUploader;
    }

    public void setBookUploader(String bookUploader) {
        this.bookUploader = bookUploader;
    }

    public Integer getBookPoints() {
        return bookPoints;
    }

    public void setBookPoints(Integer bookPoints) {
        this.bookPoints = bookPoints;
    }

    public String getBookProfile() {
        return bookProfile;
    }

    public void setBookProfile(String bookProfile) {
        this.bookProfile = bookProfile;
    }

    public String getBookCoverUrl() {
        return bookCoverUrl;
    }

    public void setBookCoverUrl(String bookCoverUrl) {
        this.bookCoverUrl = bookCoverUrl;
    }

    public String getBookCoverUuid() {
        return bookCoverUuid;
    }

    public void setBookCoverUuid(String bookCoverUuid) {
        this.bookCoverUuid = bookCoverUuid;
    }

    public String getBookFileUuid() {
        return bookFileUuid;
    }

    public void setBookFileUuid(String bookFileUuid) {
        this.bookFileUuid = bookFileUuid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryAlias() {
        return categoryAlias;
    }

    public void setCategoryAlias(String categoryAlias) {
        this.categoryAlias = categoryAlias;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    private LocalDate updateTime;
}