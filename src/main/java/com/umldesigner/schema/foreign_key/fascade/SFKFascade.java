package com.umldesigner.schema.foreign_key.fascade;

import com.umldesigner.schema.foreign_key.dto.SFKPojo;

public interface SFKFascade {
    // NOTE if this has lots of methods (more than 5) fascade design pattern should
    // be fully implemented

    /**
     * makes sure that the foreign key isn't created between item of the same table,
     * that the table's "OnDelete", "OnUpdate" values are valid,
     * and that the pojo's identity matches fUuid and sUuid
     *
     * the identity check is failsafe since the user shouldn't be able to add
     * identity since he has no access to the id's of the items so the programmer
     * should manage that
     * 
     * @param fUuid uuid of the first item
     * @param sUuid uuid of the second item
     * @param pojo  the pojo that is being checked
     * @return true if the given foreign key is valid
     * @see {@link #sameTableFKCheck(String, String)},
     *      {@link #validArgumentsCheck(SFKPojo)},
     *      {@link #fkIdentityMatch(String, String, SFKPojo)}
     */
    public boolean isValid(String fUuid, String sUuid, SFKPojo pojo);

    /**
     * checks whether the items belong to the same table
     * 
     * @param fUuid id of the first item
     * @param sUUid id of the second item
     * @return true if the given items belong to the same table
     * @apiNote this should be moved to a SItemFascade if other components have need
     *          of this
     */
    public boolean sameTableFKCheck(String fUuid, String sUUid);

    /**
     * checks whether the given given arguments about the foreign key are valid,
     * arguments like "OnDelete", "OnUpdate"
     * 
     * @param pojo input pojo
     * @return true if the arguments are valid
     */
    public boolean validArgumentsCheck(SFKPojo pojo);

    /**
     * checks whether the identity created using the fUuid and sUuid match the
     * identity in the given pojo
     * 
     * @param fUuid uuid of the first item
     * @param sUuid uuid of the second item
     * @param pojo  the given pojo whose identity is being checked against
     * @return true if both identities are equal
     * @see {@link com.umldesigner.infrastructure.pojo.identities.BaseMIdentityPojo}
     */
    public boolean fkIdentityMatch(String fUuid, String sUuid, SFKPojo pojo);

}
