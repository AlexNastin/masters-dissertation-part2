var target_db = db.getMongo().getDB("shared-storage");
if (target_db == "shared-storage") {
    if (target_db.getUser("master") == null) {
        target_db.createUser({
            user: "master",
            pwd: "master123",
            roles: [{role: "dbOwner", db: "shared-storage"}]
        });
        print('User created.')
    } else {
        print('User already exists.')
    }
}