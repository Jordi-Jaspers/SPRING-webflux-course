package com.jordijaspers.webfluxapiannotations.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

/**
 * The result retrieved from the Star Wars API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StarWarsResult {

    /**
     * The amount of results.
     */
    private int count;

    /**
     * The next page of the result.
     */
    private String next;

    /**
     * The previous page of the result.
     */
    private String previous;

    /**
     * The list of results.
     */
    private List<StarWarsCharacter> results;

    /**
     * The default constructor.
     *
     * @param count The amount of results.
     * @param next The next page of the result.
     * @param previous The previous page of the result.
     * @param results The list of results.
     */
    public StarWarsResult(final int count, final String next, final String previous,
            final List<StarWarsCharacter> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    /**
     * Empty constructor.
     */
    public StarWarsResult() {
        // EMPTY
    }

    /**
     * Getter for count.
     *
     * @return count .
     */
    public int getCount() {
        return count;
    }

    /**
     * Setter for count.
     *
     * @param count .
     */
    public void setCount(final int count) {
        this.count = count;
    }

    /**
     * Getter for next.
     *
     * @return next .
     */
    public String getNext() {
        return next;
    }

    /**
     * Setter for next.
     *
     * @param next .
     */
    public void setNext(final String next) {
        this.next = next;
    }

    /**
     * Getter for previous.
     *
     * @return previous .
     */
    public String getPrevious() {
        return previous;
    }

    /**
     * Setter for previous.
     *
     * @param previous .
     */
    public void setPrevious(final String previous) {
        this.previous = previous;
    }

    /**
     * Getter for results.
     *
     * @return results .
     */
    public List<StarWarsCharacter> getResults() {
        return results;
    }

    /**
     * Setter for results.
     *
     * @param results .
     */
    public void setResults(final List<StarWarsCharacter> results) {
        this.results = results;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final StarWarsResult that = (StarWarsResult) o;
        return count == that.count && Objects.equals(next, that.next) && Objects.equals(previous, that.previous)
                && Objects.equals(results, that.results);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(count, next, previous, results);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public String toString() {
        return "StarWarsResult{" +
                "count=" + count +
                ", next='" + next + '\'' +
                ", previous='" + previous + '\'' +
                ", results=" + results +
                '}';
    }
}
