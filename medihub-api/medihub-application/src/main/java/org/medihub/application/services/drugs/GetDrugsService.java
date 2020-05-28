package org.medihub.application.services.drugs;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.drugs.GetDrugsOutput;
import org.medihub.application.ports.incoming.drugs.GetDrugsQuery;
import org.medihub.application.ports.outgoing.drugs.GetDrugsPort;
import org.medihub.domain.Drug;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class GetDrugsService implements GetDrugsQuery {
    private final GetDrugsPort getDrugsPort;

    @Override
    public List<GetDrugsOutput> getDrugs() {
        List<Drug> drugs = getDrugsPort.getDrugs();
        List<GetDrugsOutput> drugsOut = new ArrayList<GetDrugsOutput>();

        for(Drug d : drugs) {
            drugsOut.add(new GetDrugsOutput(
                    d.getId(),
                    d.getName()
            ));
        }

        return drugsOut;
    }
}
