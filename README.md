
# SeApps abstraction
### My project (REST + SOA API) powered by Spring framework

#### For frontend dev (**Eugene Sukharev**):
* **@Nn** annotation means *not neccesary* and marked param as as non-required
* **@Rest** annotation marked the following content as a resource path
* **@Soa** annotation marked the following content as a calling functions path

## List API (23 at least):

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
    "id": 1052,
    "status": "ACTIVE",
    "name": "AfterOptimization",
    "description": "",
    "user": null,
    "components": [
        {
            "id": 1055,
            "name": "TestBlock",
            "description": "",
            "type": "block",
            "color": null,
            "x": 1,
            "y": 2,
            "relations": [
                {
                    "id": 1060,
                    "component_id": 1058,
                    "type": "abstraction",
                    "name": null,
                    "color": null
                }
            ],
            "annotated": [
                {
                    "id": 1053,
                    "name": "Корень",
                    "color": "#000000"
                }
            ]
        }
    ],
    "annotations": [
        {
            "id": 1053,
            "name": "Корень",
            "color": "#000000"
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
    "id": 361,
    "status": "ACTIVE",
    "name": "Лампа",
    "description": "Прибор для освещения (разума Жеки, хотя чего его освещать, он и так светлый)",
    "user": null,
    "num_cell": 9,
    "type": "block",
    "color": "#636363",
    "title_to": null,
    "title_from": null,
    "files": [],
    "relations": [
        {
            "relation_id": 364,
            "component_id": 363,
            "name": "Свет",
            "type": null,
            "mode": "from",
            "description": null,
            "color": "#ffff00"
        },
        {
            "relation_id": 368,
            "component_id": 367,
            "name": "Фонарь",
            "type": null,
            "mode": "to",
            "description": null,
            "color": "#000000"
        }
    ],
    "annotated": [],
    "workspace_id": 360
}
```

### GET /component/{id}/local
**Response:** response example
```
{
    "id": 1053,
    "name": "Корень",
    "workspace_id": 1052,
    "annotations": [
        {
            "id": 1053,
            "name": "Корень",
            "color": "#000000"
        }
    ],
    "components": [
        {
            "id": 1055,
            "name": "TestBlock",
            "description": "",
            "type": "block",
            "color": null,
            "x": 1,
            "y": 2,
            "relations": [],
            "annotated": []
        }
    ]
}
```

### POST /component
**Params:**
* **@Nn** name
* **@Nn** description
* workspace_id
* x
* y

**Response:** status code

### POST /component/{component_id}
**Params:**
* **@Nn** name
* **@Nn** description
* workspace_id
* x
* y

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
* x **int**
* y **int**
* parent_id **int**

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
