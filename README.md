Where the docs? It loosed! Recovering...
# SeApps abstraction
### My project (REST + SOA API) powered by Spring framework

#### For frontend dev (**Eugene Sukharev**):
* **@Nn** annotation means *not neccesary* and marked param as as non-required
* **@Rest** annotation marked the following content as a resource path
* **@Soa** annotation marked the following content as a calling functions path

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
            "id": 151,
            "status": "ACTIVE",
            "name": "Component added",
            "description": null,
            "user": null,
            "num_cell": 4,
            "type": "block",
            "color": null
        },
        {
            "id": 189,
            "status": "ACTIVE",
            "name": "Новый блок",
            "description": null,
            "user": null,
            "num_cell": 16,
            "type": "block",
            "color": null
        }
    ],
    "relations": [
        {
            "id": 146,
            "status": "ACTIVE",
            "name": "My relation",
            "description": null,
            "user": null,
            "componentTo": {
                "id": 143,
                "status": "ACTIVE",
                "name": "SecAdComponent",
                "description": null,
                "user": null,
                "num_cell": 0,
                "type": "relationship",
                "color": null
            },
            "type": "abstraction",
            "componentFrom": {
                "id": 142,
                "status": "ACTIVE",
                "name": "Added сomponent",
                "description": "Description",
                "user": null,
                "num_cell": 10,
                "type": "block",
                "color": null
            }
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
    "id": 142,
    "status": "ACTIVE",
    "name": "Added сomponent",
    "description": "Description",
    "user": null,
    "num_cell": 10,
    "type": "block",
    "color": null,
    "files": [
        {
            "id": 147,
            "status": "ACTIVE",
            "name": "image",
            "description": "yjdt",
            "path": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmaWxlbmFtZSIsIm5hbWUiOiJpbWFnZSIsImlhdCI6MTU3NjQ1NDI1NX0.UoeHp9uM7br_pmPBKlo6uNFdoAv7TcwdWfpZ0PbEH08",
            "type": "img"
        }
    ],
    "relationsAsBlocks": [
        {
            "id": 146,
            "status": "ACTIVE",
            "name": "My relation",
            "description": null,
            "user": null,
            "componentTo": null,
            "type": "abstraction",
            "componentFrom": {
                "id": 142,
                "status": "ACTIVE",
                "name": "Added сomponent",
                "description": "Description",
                "user": null,
                "num_cell": 10,
                "type": "block",
                "color": null
            }
        }
    ],
    "relationsAsRelations": [],
    "workspace_id": 138
}
```

### POST /component
**Params:**
* **@Nn** name
* **@Nn** description
* workspace_id
* num_cell
**Response:** status code

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
