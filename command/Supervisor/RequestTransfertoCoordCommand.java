package command.Supervisor;

import java.time.LocalDateTime;
import command.Command;
import FYPMS.FYPMS;
import FYPMS.project.*;
import FYPMS.faculty.supervisor.*;
import FYPMS.request.*;

public class RequestTransfertoCoordCommand implements Command {
    private String requester;
    
    public RequestTransfertoCoordCommand(String requester){
        this.requester = requester;
    }
    RequestList RequestList = FYPMS.getRequestList();

    public void execute() {
        LocalDateTime statusChangeTime = LocalDateTime.now();
        Request request = new Request(requester, RequestType.SUPERVISORCoordinator, statusChangeTime,
                "FYP Coordinator", RequestStatus.PENDING);
        RequestList.add(request);
    }
}
