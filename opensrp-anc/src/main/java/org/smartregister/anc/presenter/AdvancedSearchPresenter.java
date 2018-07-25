package org.smartregister.anc.presenter;

import org.smartregister.anc.contract.AdvancedSearchContract;
import org.smartregister.anc.cursor.AdvancedMatrixCursor;
import org.smartregister.anc.interactor.AdvancedSearchInteractor;
import org.smartregister.anc.model.AdvancedSearchModel;
import org.smartregister.anc.util.DBConstants;
import org.smartregister.domain.Response;

import java.lang.ref.WeakReference;
import java.util.Map;

public class AdvancedSearchPresenter extends RegisterFragmentPresenter implements AdvancedSearchContract.Presenter, AdvancedSearchContract.InteractorCallBack {

    private WeakReference<AdvancedSearchContract.View> viewReference;

    private AdvancedSearchContract.Model model;

    private AdvancedSearchContract.Interactor interactor;

    private AdvancedMatrixCursor matrixCursor;

    public AdvancedSearchPresenter(AdvancedSearchContract.View view, String viewConfigurationIdentifier) {
        super(view, viewConfigurationIdentifier);
        this.viewReference = new WeakReference<>(view);
        model = new AdvancedSearchModel();
        interactor = new AdvancedSearchInteractor();
    }

    public void search(String firstName, String lastName, String ancId, String edd, String dob, String phoneNumber, String alternateContact, boolean isLocal) {
        Map<String, String> editMap = model.createEditMap(firstName, lastName, ancId, edd, dob, phoneNumber, alternateContact, isLocal);
        if (editMap == null || editMap.isEmpty()) {
            return;
        }

        if (isLocal) {
            String mainCondition = model.getMainConditionString(editMap);
            String tableName = DBConstants.WOMAN_TABLE_NAME;

            String countSelect = model.countSelect(tableName, mainCondition);
            String mainSelect = model.mainSelect(tableName, mainCondition);

            getView().initializeQueryParams(tableName, countSelect, mainSelect);
            getView().initializeAdapter(visibleColumns);

            getView().countExecute();
            getView().filterandSortInInitializeQueries();

            getView().refresh();

            getView().switchViews(true);
        } else {
            interactor.search(editMap, this);

        }
    }

    @Override
    public void onResultsFound(Response<String> response) {
        matrixCursor = model.createMatrixCursor(response);

        getView().recalculatePagination(matrixCursor);

        getView().filterandSortInInitializeQueries();
        getView().refresh();

        getView().switchViews(true);


    }

    public AdvancedMatrixCursor getMatrixCursor() {
        return matrixCursor;
    }

    protected AdvancedSearchContract.View getView() {
        if (viewReference != null)
            return viewReference.get();
        else
            return null;
    }
}
