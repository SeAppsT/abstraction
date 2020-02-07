
# SeApps abstraction
### My project (REST + SOA API) powered by Spring framework

#### For frontend dev (**Eugene Sukharev**):
* **@Nn** annotation means *not neccesary* and marked param as as non-required
* **@Rest** annotation marked the following content as a resource path
* **@Soa** annotation marked the following content as a calling functions path

## List API (22 at least):

## Auth/User

### POST /auth
**Params:**
* login
* password

**Response:** response example
```
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MiIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE1Nzc5MTEwMjIsImV4cCI6MTU3Nzk5NzQyMn0.aWuF0kyajgY7sjjIJBo_bBCH4hWzqUvkumRZkyXavrM",
    "user": {
        "id": 121,
        "status": "ACTIVE",
        "name": null,
        "description": null,
        "login": "test2",
        "password": "$2a$10$jY1Q1FjjLk.gpwSMec4AbeBQaznzi6skWifLyUt6INUyZlQVvsNUC",
        "roles": [
            {
                "id": 1,
                "status": "ACTIVE",
                "name": "ROLE_USER",
                "description": null
            }
        ]
    }
}
```

### POST /user
**Params:**
* login
* password
* **@Nn** name
* **@Nn** description

**Response:** status code

## Workspace
### *@Rest*

### GET /workspace

**Response:** response example
```
[
    {
        "id": 138,
        "status": "ACTIVE",
        "name": "workspace by new spring",
        "description": "it's my new workspace",
        "user": null,
        "components": null,
        "relations": null
    },
    {
        "id": 149,
        "status": "ACTIVE",
        "name": "Second",
        "description": "second workspaceмроп",
        "user": null,
        "components": null,
        "relations": null
    }
```

### GET /workspace/{id}

**Response:** response example
```
{
    "id": 138,
    "status": "ACTIVE",
    "name": "workspace by new spring",
    "description": "it's my new workspace",
    "user": null,
    "components": [
        {
            "id": 180,
            "status": "ACTIVE",
            "name": "V6",
            "description": "This block is deprecated",
            "user": null,
            "num_cell": 1,
            "type": "block",
            "color": null,
            "relations": [
                {
                    "component_id": 156,
                    "type": "abstraction",
                    "name": null,
                    "color": ""
                }
            ]
        },
        {
            "id": 159,
            "status": "ACTIVE",
            "name": "Смотрю",
            "description": null,
            "user": null,
            "num_cell": 7,
            "type": "block",
            "color": null,
            "relations": []
        },
        {
            "id": 151,
            "status": "ACTIVE",
            "name": "Component added",
            "description": "Кто addнул this component?",
            "user": null,
            "num_cell": 4,
            "type": "block",
            "color": null,
            "relations": []
        }
    ]
}
```

### POST /workspace
**Params:**
* **@Nn** name
* **@Nn** description
**Response:** status code

### PUT /workspace/{id}
**Params:**
* **@Nn** name
* **@Nn** description
**Response:** status code

### DELETE /workspace/{id}

**Response:** status code

## Component
### *@Rest*

### GET /component/{id}
**Response:** response example

```
{
    "id": 180,
    "status": "ACTIVE",
    "name": "V6",
    "description": "This block is deprecated",
    "user": null,
    "num_cell": 1,
    "type": "block",
    "color": null,
    "files": [],
    "relations": [
        {
            "relation_id": 476,
            "component_id": 159,
            "name": "Смотрю",
            "type": null,
            "mode": "from",
            "description": null,
            "color": null
        },
        {
            "relation_id": 474,
            "component_id": 254,
            "name": "cgjfgjhn",
            "type": null,
            "mode": "from",
            "description": null,
            "color": null
        },
        {
            "relation_id": 475,
            "component_id": 334,
            "name": "Александр Блок",
            "type": null,
            "mode": "from",
            "description": null,
            "color": null
        }
    ],
    "annotated": [],
    "workspace_id": 138
}
```

### GET /component/{id}/local
**Response:** response example
```
{
    "id": 189,
    "name": "И чо дальше?",
    "components": [
        {
            "id": 354,
            "status": "ACTIVE",
            "name": "ElseOneEditedComponent",
            "description": "",
            "user": null,
            "num_cell": 8,
            "type": "block",
            "color": null,
            "relations": null
        }
    ]
}
```

### POST /component
**Params:**
* **@Nn** name
* **@Nn** description
* workspace_id
* num_cell
**Response:** status code

### POST /component/{component_id}
**Params:**
* **@Nn** name
* **@Nn** description
* workspace_id
* num_cell
**Response:** status code
**Note:** to create components with relation by one step

### PUT /component/{id}
**Params:**
* **@Nn** name
* **@Nn** description


**Response:** status code

### DELETE /component/{id}

**Response:** status code

### *@Soa*

### POST /component/{id}/cast/block
**Params:** none

**Response:** status code

**Note:** *changes type of component to* **block**

### POST /component/{id}/cast/relation
**Params:** none

**Response:** status code

**Note:** *changes type of component to* **relation**

### POST /component/{id}/position
**Params:**
* num_cell: **int**

**Response:** status code

**Note:** *changes position of component*

## Relation
### *@Rest*

### POST /relation
**Params:**
* **@Nn** name
* **@Nn** description
* component_to_id
* component_from_id

**Response:** status code

### PUT /relation/{id}
**Params:**
* **@Nn** name
* **@Nn** description
* **@Nn** component_to_id
* **@Nn** component_from_id

**Response:** status code

### DELETE /relation/{id}

**Response:** status code

## Media
### *@Rest*

### GET /media/{id}

**Response:** response example
```
{
    "id": 147,
    "status": "ACTIVE",
    "name": "image",
    "description": "yjdt",
    "path": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmaWxlbmFtZSIsIm5hbWUiOiJpbWFnZSIsImlhdCI6MTU3NjQ1NDI1NX0.UoeHp9uM7br_pmPBKlo6uNFdoAv7TcwdWfpZ0PbEH08",
    "type": "img"
}
```

### POST /media
**Params:**
* **@Nn** name
* **@Nn** description
* type
* component_id

**Response:** status code

### PUT /media/{id}
**Params:**
* **@Nn** name
* **@Nn** description

**Response:** status code

### DELETE /media/{id}

**Response:** status code
