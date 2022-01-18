package com.jordijaspers.webfluxapiannotations.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a Star Wars character.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StarWarsCharacter {

    /**
     * The name of the character.
     */
    private String name;

    /**
     * The gender of the character.
     */
    private String gender;

    /**
     * The birth year of the character.
     */
    private String birthYear;

    /**
     * The eye Color of the character.
     */
    private String eyeColor;

    /**
     * The hair Color of the character.
     */
    private String hairColor;

    /**
     * The height of the character.
     */
    private String height;

    /**
     * A constructor to define a StarWarsCharacter.
     *
     * @param name      The name of the character.
     * @param gender    The gender of the character.
     * @param birthYear The birth year of the character.
     * @param eyeColor  The eye Color of the character.
     * @param hairColor The hair Color of the character.
     * @param height    The height of the character.
     */
    public StarWarsCharacter(
            final String name,
            final String gender,
            final String birthYear,
            final String eyeColor,
            final String hairColor,
            final String height) {
        this.name = name;
        this.gender = gender;
        this.birthYear = birthYear;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.height = height;
    }

    /**
     * The default constructor.
     */
    public StarWarsCharacter() {
        // Empty constructor
    }

    /**
     * Getter for name.
     *
     * @return name .
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name.
     *
     * @param name .
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Getter for gender.
     *
     * @return gender .
     */
    public String getGender() {
        return gender;
    }

    /**
     * Setter for gender.
     *
     * @param gender .
     */
    public void setGender(final String gender) {
        this.gender = gender;
    }

    /**
     * Getter for birthYear.
     *
     * @return birthYear .
     */
    public String getBirthYear() {
        return birthYear;
    }

    /**
     * Setter for birthYear.
     *
     * @param birthYear .
     */
    public void setBirthYear(final String birthYear) {
        this.birthYear = birthYear;
    }

    /**
     * Getter for eyeColor.
     *
     * @return eyeColor .
     */
    public String getEyeColor() {
        return eyeColor;
    }

    /**
     * Setter for eyeColor.
     *
     * @param eyeColor .
     */
    public void setEyeColor(final String eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * Getter for hairColor.
     *
     * @return hairColor .
     */
    public String getHairColor() {
        return hairColor;
    }

    /**
     * Setter for hairColor.
     *
     * @param hairColor .
     */
    public void setHairColor(final String hairColor) {
        this.hairColor = hairColor;
    }

    /**
     * Getter for height.
     *
     * @return height .
     */
    public String getHeight() {
        return height;
    }

    /**
     * Setter for height.
     *
     * @param height .
     */
    public void setHeight(final String height) {
        this.height = height;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public String toString() {
        return "StarWarsCharacter{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", height='" + height + '\'' +
                '}';
    }
}
