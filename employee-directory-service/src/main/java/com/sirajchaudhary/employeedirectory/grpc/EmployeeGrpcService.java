package com.sirajchaudhary.employeedirectory.grpc;

import com.sirajchaudhary.employeedirectory.entity.Employee;
import com.sirajchaudhary.employeedirectory.mapper.EmployeeMapper;
import com.sirajchaudhary.employeedirectory.service.EmployeeService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.grpc.server.service.GrpcService;

/*
============================================================================
Employee gRPC API Endpoint
============================================================================

This class implements all Employee gRPC APIs defined in the employee.proto
contract by extending the generated EmployeeServiceGrpc.EmployeeServiceImplBase
class.

Responsibilities:

• Acts as the gRPC API endpoint for all Employee operations.
• Receives Protocol Buffer request messages from gRPC clients.
• Delegates business logic to the EmployeeService layer.
• Converts Protocol Buffer messages into Employee entities.
• Converts Employee entities into Protocol Buffer response messages.
• Sends responses back to gRPC clients using StreamObserver.
• Implements the Unary RPC communication pattern.
• Contains only transport logic. Business logic resides in the service layer.

Request Flow:

      gRPC Client
           │
           ▼
 EmployeeGrpcService
           │
           ▼
    EmployeeService
           │
           ▼
 EmployeeRepository
           │
           ▼
      PostgreSQL

Important Notes:

• EmployeeServiceGrpc.EmployeeServiceImplBase is generated automatically
  from the employee.proto contract during the Maven build.

• Every RPC method defined in employee.proto becomes an overridable Java
  method inside EmployeeServiceImplBase.

• Request and response classes such as CreateEmployeeRequest,
  EmployeeResponse, EmployeeListResponse, ExistsResponse, etc. are
  generated automatically from the employee.proto contract.

• These request and response classes are Protocol Buffer message classes,
  not manually written DTOs.

• StreamObserver<T> is provided by the gRPC framework and is responsible
  for sending responses from the server back to the client.

• Since this project uses Unary RPC, every API receives exactly one
  request message and returns exactly one response message.

============================================================================
*/
@GrpcService
@RequiredArgsConstructor
public class EmployeeGrpcService extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    /*
    ============================================================================
    Create Employee API
    ============================================================================

    Creates a new employee.

    Request:
    • CreateEmployeeRequest is a Protocol Buffer message generated
      automatically from the CreateEmployeeRequest message defined
      in employee.proto.

    Response:
    • EmployeeResponse is a Protocol Buffer message generated from
      employee.proto and returned to the client.

    StreamObserver:
    • Used by the gRPC server to send the response back to the client.
    • onNext() sends the response.
    • onCompleted() marks the RPC call as completed.

    Communication Pattern:
    • Unary RPC (One Request -> One Response)
    ============================================================================
    */
    @Override
    public void createEmployee(CreateEmployeeRequest request,
                               StreamObserver<EmployeeResponse> responseObserver) {

        Employee employee = employeeMapper.toEntity(request);

        Employee savedEmployee = employeeService.createEmployee(employee);

        EmployeeResponse response = EmployeeResponse.newBuilder()
                .setEmployee(employeeMapper.toMessage(savedEmployee))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    /*
    ============================================================================
    Get Employee API
    ============================================================================

    Retrieves an employee using its unique ID.

    Request:
    • GetEmployeeRequest is generated automatically from
      employee.proto and contains the employee ID.

    Response:
    • EmployeeResponse contains the employee details returned
      to the gRPC client.

    StreamObserver:
    • Sends the response back to the client.
    • onNext() sends the employee details.
    • onCompleted() completes the RPC call.

    Communication Pattern:
    • Unary RPC (One Request -> One Response)
    ============================================================================
    */
    @Override
    public void getEmployee(GetEmployeeRequest request,
                            StreamObserver<EmployeeResponse> responseObserver) {

        Employee employee = employeeService.getEmployee(request.getId());

        EmployeeResponse response = EmployeeResponse.newBuilder()
                .setEmployee(employeeMapper.toMessage(employee))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    /*
    ============================================================================
    Get All Employees API
    ============================================================================

    Retrieves all employees with pagination and sorting support.

    Request:
    • GetAllEmployeesRequest is generated automatically from employee.proto.
    • It contains:
      - page            : Zero-based page number.
      - size            : Number of records per page.
      - sortBy          : Field used for sorting.
      - sortDirection   : ASC or DESC.

    Response:
    • EmployeeListResponse is generated from employee.proto.
    • It contains:
      - List of employees.
      - Total records.
      - Total pages.
      - Current page.
      - Page size.

    StreamObserver:
    • Sends the EmployeeListResponse back to the client.
    • onNext() sends the response.
    • onCompleted() completes the RPC call.

    Communication Pattern:
    • Unary RPC (One Request -> One Response)
    ============================================================================
    */
    @Override
    public void getAllEmployees(GetAllEmployeesRequest request,
                                StreamObserver<EmployeeListResponse> responseObserver) {

        Pageable pageable = createPageable(
                request.getPage(),
                request.getSize(),
                request.getSortBy(),
                request.getSortDirection()
        );

        Page<Employee> page = employeeService.getAllEmployees(pageable);

        EmployeeListResponse.Builder response = EmployeeListResponse.newBuilder()
                .setTotalElements(page.getTotalElements())
                .setTotalPages(page.getTotalPages())
                .setCurrentPage(page.getNumber())
                .setPageSize(page.getSize());

        page.getContent()
                .stream()
                .map(employeeMapper::toMessage)
                .forEach(response::addEmployees);

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    /*
    ============================================================================
    Update Employee API
    ============================================================================

    Updates an existing employee.

    Request:
    • UpdateEmployeeRequest is generated automatically from employee.proto.
    • It contains the employee ID along with the updated employee details.

    Response:
    • EmployeeResponse contains the updated employee details returned
      to the gRPC client.

    StreamObserver:
    • Sends the updated employee information back to the client.
    • onNext() sends the response.
    • onCompleted() completes the RPC call.

    Communication Pattern:
    • Unary RPC (One Request -> One Response)
    ============================================================================
    */
    @Override
    public void updateEmployee(UpdateEmployeeRequest request,
                               StreamObserver<EmployeeResponse> responseObserver) {

        Employee employee = employeeMapper.toEntity(request);

        Employee updatedEmployee = employeeService.updateEmployee(employee);

        EmployeeResponse response = EmployeeResponse.newBuilder()
                .setEmployee(employeeMapper.toMessage(updatedEmployee))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    /*
    ============================================================================
    Delete Employee API
    ============================================================================

    Deletes an employee using its unique ID.

    Request:
    • DeleteEmployeeRequest is generated automatically from employee.proto.
    • It contains the employee ID to be deleted.

    Response:
    • DeleteEmployeeResponse contains:
      - Success status.
      - Response message.

    StreamObserver:
    • Sends the deletion result back to the client.
    • onNext() sends the response.
    • onCompleted() completes the RPC call.

    Communication Pattern:
    • Unary RPC (One Request -> One Response)
    ============================================================================
    */
    @Override
    public void deleteEmployee(DeleteEmployeeRequest request,
                               StreamObserver<DeleteEmployeeResponse> responseObserver) {

        employeeService.deleteEmployee(request.getId());

        DeleteEmployeeResponse response = DeleteEmployeeResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Employee deleted successfully.")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    /*
    ============================================================================
    Search Employees API
    ============================================================================

    Searches employees using a keyword with pagination and sorting.

    Request:
    • SearchEmployeesRequest is generated automatically from employee.proto.
    • It contains:
      - keyword         : Search keyword.
      - page            : Zero-based page number.
      - size            : Number of records per page.
      - sortBy          : Field used for sorting.
      - sortDirection   : ASC or DESC.

    Search Fields:
    • Employee Code
    • First Name
    • Last Name

    Features:
    • Partial keyword search.
    • Case-insensitive search.
    • Pagination support.
    • Sorting support.

    Response:
    • EmployeeListResponse contains:
      - Matching employees.
      - Total records.
      - Total pages.
      - Current page.
      - Page size.

    StreamObserver:
    • Sends the search result back to the client.
    • onNext() sends the response.
    • onCompleted() completes the RPC call.

    Communication Pattern:
    • Unary RPC (One Request -> One Response)
    ============================================================================
    */
    @Override
    public void searchEmployees(SearchEmployeesRequest request,
                                StreamObserver<EmployeeListResponse> responseObserver) {

        Pageable pageable = createPageable(
                request.getPage(),
                request.getSize(),
                request.getSortBy(),
                request.getSortDirection()
        );

        Page<Employee> page = employeeService.searchEmployees(
                request.getKeyword(),
                pageable
        );

        EmployeeListResponse.Builder response = EmployeeListResponse.newBuilder()
                .setTotalElements(page.getTotalElements())
                .setTotalPages(page.getTotalPages())
                .setCurrentPage(page.getNumber())
                .setPageSize(page.getSize());

        page.getContent()
                .stream()
                .map(employeeMapper::toMessage)
                .forEach(response::addEmployees);

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    /*
    ============================================================================
    Find Employees By Department API
    ============================================================================

    Retrieves employees belonging to a specific department with
    pagination and sorting support.

    Request:
    • FindEmployeesByDepartmentRequest is generated automatically
      from employee.proto.
    • It contains:
      - department      : Department name.
      - page            : Zero-based page number.
      - size            : Number of records per page.
      - sortBy          : Field used for sorting.
      - sortDirection   : ASC or DESC.

    Features:
    • Department-based filtering.
    • Pagination support.
    • Sorting support.

    Response:
    • EmployeeListResponse contains:
      - Employee list.
      - Total records.
      - Total pages.
      - Current page.
      - Page size.

    StreamObserver:
    • Sends the employee list back to the client.
    • onNext() sends the response.
    • onCompleted() completes the RPC call.

    Communication Pattern:
    • Unary RPC (One Request -> One Response)
    ============================================================================
    */
    @Override
    public void findEmployeesByDepartment(FindEmployeesByDepartmentRequest request,
                                          StreamObserver<EmployeeListResponse> responseObserver) {

        Pageable pageable = createPageable(
                request.getPage(),
                request.getSize(),
                request.getSortBy(),
                request.getSortDirection()
        );

        Page<Employee> page = employeeService.findEmployeesByDepartment(
                request.getDepartment(),
                pageable
        );

        EmployeeListResponse.Builder response = EmployeeListResponse.newBuilder()
                .setTotalElements(page.getTotalElements())
                .setTotalPages(page.getTotalPages())
                .setCurrentPage(page.getNumber())
                .setPageSize(page.getSize());

        page.getContent()
                .stream()
                .map(employeeMapper::toMessage)
                .forEach(response::addEmployees);

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    /*
    ============================================================================
    Exists By Employee Code API
    ============================================================================

    Checks whether an employee code already exists.

    Request:
    • ExistsByEmployeeCodeRequest is generated automatically
      from employee.proto.
    • It contains the employee code to be verified.

    Response:
    • ExistsResponse is generated automatically from employee.proto.
    • It contains a boolean value indicating whether the employee
      code exists.

    StreamObserver:
    • Sends the validation result back to the client.
    • onNext() sends the response.
    • onCompleted() completes the RPC call.

    Communication Pattern:
    • Unary RPC (One Request -> One Response)
    ============================================================================
    */
    @Override
    public void existsByEmployeeCode(ExistsByEmployeeCodeRequest request,
                                     StreamObserver<ExistsResponse> responseObserver) {

        ExistsResponse response = ExistsResponse.newBuilder()
                .setExists(employeeService.existsByEmployeeCode(request.getEmployeeCode()))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    /*
    ============================================================================
    Exists By Email API
    ============================================================================

    Checks whether an email address already exists.

    Request:
    • ExistsByEmailRequest is generated automatically
      from employee.proto.
    • It contains the email address to be verified.

    Response:
    • ExistsResponse is generated automatically from employee.proto.
    • It contains a boolean value indicating whether the email
      address exists.

    StreamObserver:
    • Sends the validation result back to the client.
    • onNext() sends the response.
    • onCompleted() completes the RPC call.

    Communication Pattern:
    • Unary RPC (One Request -> One Response)
    ============================================================================
    */
    @Override
    public void existsByEmail(ExistsByEmailRequest request,
                              StreamObserver<ExistsResponse> responseObserver) {

        ExistsResponse response = ExistsResponse.newBuilder()
                .setExists(employeeService.existsByEmail(request.getEmail()))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    /*
    ============================================================================
    Count Employees API
    ============================================================================

    Returns the total number of employees available in the database.

    Request:
    • CountEmployeesRequest is generated automatically
      from employee.proto.
    • This request does not contain any fields because no
      input parameters are required.

    Response:
    • CountResponse is generated automatically from employee.proto.
    • It contains the total employee count.

    StreamObserver:
    • Sends the total employee count back to the client.
    • onNext() sends the response.
    • onCompleted() completes the RPC call.

    Communication Pattern:
    • Unary RPC (One Request -> One Response)
    ============================================================================
    */
    @Override
    public void countEmployees(CountEmployeesRequest request,
                               StreamObserver<CountResponse> responseObserver) {

        CountResponse response = CountResponse.newBuilder()
                .setCount(employeeService.countEmployees())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    /*
    ============================================================================
    Create Pageable
    ============================================================================

    Creates a Spring Data Pageable object using the pagination
    and sorting parameters received from the gRPC request.

    Parameters:
    • page            : Zero-based page number.
    • size            : Number of records per page.
    • sortBy          : Entity field used for sorting.
    • sortDirection   : ASC or DESC.

    Returns:
    • Pageable object used by the service and repository layers
      to perform pagination and sorting.

    Note:
    • This is an internal helper method and is not exposed
      as a gRPC API.
    ============================================================================
    */
    private Pageable createPageable(int page,
                                    int size,
                                    String sortBy,
                                    String sortDirection) {

        Sort.Direction direction = "DESC".equalsIgnoreCase(sortDirection)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        return PageRequest.of(page, size, Sort.by(direction, sortBy));
    }
}