package command.Student;

import java.time.LocalDateTime;

import FYPMS.FYPMS;
import FYPMS.request.Request;
import FYPMS.request.RequestList;
import FYPMS.request.RequestStatus;
import FYPMS.request.RequestRelationship;
import command.Command;

public class RequestCoordDeregisterCommand implements Command {
    private String requesterName;

    public RequestCoordDeregisterCommand(String requesterName) {
        this.requesterName = requesterName;
    }

    RequestList RequestList = FYPMS.getRequestList();

    public void execute() {
        LocalDateTime statusChangeTime = LocalDateTime.now();
        Request request = new Request(requesterName, RequestRelationship.STUDENTCoordinator, statusChangeTime,
                "FYP Coordinator", RequestStatus.PENDING);
        RequestList.add(request);
    }
}
