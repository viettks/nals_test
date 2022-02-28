# nals_test
---
## API CREATE
### url: http://localhost:9999/api/work 
### method : POST 
|Tham số      |Mô tả                                             |Bắt buột |
| ------------|:------------------------------------------------:| -------:|
|name         |work name                                    |Có       |
|start_date   |Ngày bắt đầu                                 |Có       |
|end_date     |Ngày kết thúc                                |Có       |
|status       |Trạng thái(1,2,3 - Planing, Doing, Complete) |Có       |
### Example : 
~~~
{
"name":"work name",
"start_date":2022-02-28,
"end_date":2022-03-15,
"status":1
}
~~~
### Return
~~~
{
"work_id":"1", // work id
"message":"message" // Kết quả thực hiện
}
~~~
---
## API UPDATE
### url: http://localhost:9999/api/work
### method : PUT
| Tham số        | Mô tả                                           | Bắt buột|
| -------------  |:-----------------------------------------------:|------- |
| work_id        | Mã công việc                                    |Có      |
| name           | work name                                       |Có      |
| start_date     | Ngày bắt đầu                                    |Có      |
| end_date       | Ngày kết thúc                                   |Có      |
| status         | Trạng thái(1,2,3 - Planing, Doing, Complete)    |Có      |
### Example : 
~~~
{
"work_id":"1",
"name":"work name",
"start_date":2022-02-28,
"end_date":2022-03-15,
"status":1
}
~~~
### Return 
~~~
{
"work_id":"1", // work id
"message":"message" // Kết quả thực hiện
}
~~~
---
## API DELETE
### url: http://localhost:9999/api/work
### method : DELETE
| Tham số        | Mô tả                                           | Bắt buột|
| -------------  |:-----------------------------------------------:| -------:|
| work_id        | Mã công việc                                    | Có      |

### Example : 
~~~
{
"work_id":"1",
}
~~~
### Return 
~~~
{
"work_id":"1", // work id
"message":"message" // Kết quả thực hiện
}
~~~
---
## API FETCH LIST
### url: http://localhost:9999/api/work/list
### method : GET
|Tham số   | Mô tả                                                                     | Bắt buột
| ---------|:-------------------------------------------------------------------------:| -------:|
|sort_type |  Sắp xếp tăng, giảm (Chỉ chấp nhận ASC, DESC)                             |Không    |
|sort_by   |  Sắp xếp theo trường(1:name ,2:starting date ,3: ending date ,4: status ) |Không    |
|page      |  Trang                                                                    |Không    |

### Example : 
~~~
{
"sort_type":"ASC",
"sort_by" : 1,
"page" : 1        
}
~~~
### Return 
~~~
{
"result":[], //Danh sách work 
}
~~~
