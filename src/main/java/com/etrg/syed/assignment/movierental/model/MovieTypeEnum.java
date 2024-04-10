package com.etrg.syed.assignment.movierental.model;

/**
 * Enum: Kept in constants, can add to enum also but making it bound at a place
 * but price may vary and easily configurable if moved to properties, at the moment it is in constants.
 * Thought of seperation of concern and easy to maintain.
 * In this project its not very much difference since it is limited number of types.
 */
public enum MovieTypeEnum {
    REGULAR, NEW_RELEASE, CHILDREN;
}