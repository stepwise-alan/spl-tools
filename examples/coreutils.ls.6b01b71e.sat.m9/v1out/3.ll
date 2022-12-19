; ModuleID = '/tmp/sea-p2i772yq/3.pp.ms.o.bc'
source_filename = "3.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@llvm.used = appending global [16 x i8*] [i8* bitcast (void ()* @seahorn.fail to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void ()* @seahorn.fail to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void ()* @seahorn.fail to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void ()* @seahorn.fail to i8*)], section "llvm.metadata"

declare i64 @_nd_st_size1() local_unnamed_addr #0

declare i64 @_nd_st_size2() local_unnamed_addr #0

declare i64 @_nd_st_atim_tv_sec1() local_unnamed_addr #0

declare i64 @_nd_st_atim_tv_sec2() local_unnamed_addr #0

declare i64 @_nd_st_ctim_tv_sec1() local_unnamed_addr #0

declare i64 @_nd_st_ctim_tv_sec2() local_unnamed_addr #0

declare i64 @_nd_st_mtim_tv_sec1() local_unnamed_addr #0

declare i64 @_nd_st_mtim_tv_sec2() local_unnamed_addr #0

declare i32 @_nd_st_mode1() local_unnamed_addr #0

declare i32 @_nd_st_mode2() local_unnamed_addr #0

declare i32 @_nd_strcoll12() local_unnamed_addr #0

declare i32 @_nd_strcmp12() local_unnamed_addr #0

declare i32 @_nd_all_fmt() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_size() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_atime() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_ctime() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_mtime() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_dir() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_reverse() local_unnamed_addr #0

declare zeroext i1 @_nd_CONFIG_LOCALE_SUPPORT() local_unnamed_addr #0

declare zeroext i1 @_nd_CONFIG_LFS() local_unnamed_addr #0

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assume(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assume.not(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @seahorn.fail() #1

; Function Attrs: inaccessiblememonly nofree norecurse noreturn nounwind
declare void @verifier.error() #2

; Function Attrs: inaccessiblememonly
declare void @seahorn.fn.enter() local_unnamed_addr #3

declare i32 @verifier.nondet.0()

; Function Attrs: nounwind uwtable
define dso_local i32 @main() local_unnamed_addr #4 {
entry:
  call void @seahorn.fn.enter() #7
  %_0 = call i64 @_nd_st_size1() #7
  %_1 = call i64 @_nd_st_size2() #7
  %_2 = call i64 @_nd_st_atim_tv_sec1() #7
  %_3 = call i64 @_nd_st_atim_tv_sec2() #7
  %_4 = call i64 @_nd_st_ctim_tv_sec1() #7
  %_5 = call i64 @_nd_st_ctim_tv_sec2() #7
  %_6 = call i64 @_nd_st_mtim_tv_sec1() #7
  %_7 = call i64 @_nd_st_mtim_tv_sec2() #7
  %_8 = call i32 @_nd_st_mode1() #7
  %_9 = call i32 @_nd_st_mode2() #7
  %_10 = call i32 @_nd_strcoll12() #7
  %_11 = call i32 @_nd_strcmp12() #7
  %_12 = call i32 @_nd_all_fmt() #7
  %_13 = call zeroext i1 @_nd_sort_size() #7
  %_14 = call zeroext i1 @_nd_sort_atime() #7
  %_15 = call zeroext i1 @_nd_sort_ctime() #7
  %_16 = call zeroext i1 @_nd_sort_mtime() #7
  %_17 = call zeroext i1 @_nd_sort_dir() #7
  %_18 = call zeroext i1 @_nd_sort_reverse() #7
  %_19 = call zeroext i1 @_nd_CONFIG_LOCALE_SUPPORT() #7
  %_20 = call zeroext i1 @_nd_CONFIG_LFS() #7
  %_21 = xor i1 %_19, true
  call void @verifier.assume(i1 %_21) #7
  %_22 = xor i1 %_19, true
  call void @verifier.assume(i1 %_22) #7
  %_23 = call i1 @nondet.bool()
  call void @verifier.assume.not(i1 %_23)
  br label %precall

precall:                                          ; preds = %entry
  br label %sortcmp

sortcmp:                                          ; preds = %precall
  call void @seahorn.fn.enter() #7
  br i1 %_13, label %_24, label %_26

_24:                                              ; preds = %sortcmp
  %_25 = sub nsw i64 %_1, %_0
  br label %_43

_26:                                              ; preds = %sortcmp
  br i1 %_14, label %_27, label %_29

_27:                                              ; preds = %_26
  %_28 = sub nsw i64 %_3, %_2
  br label %_43

_29:                                              ; preds = %_26
  br i1 %_15, label %_30, label %_32

_30:                                              ; preds = %_29
  %_31 = sub nsw i64 %_5, %_4
  br label %_43

_32:                                              ; preds = %_29
  br i1 %_16, label %_33, label %_35

_33:                                              ; preds = %_32
  %_34 = sub nsw i64 %_7, %_6
  br label %_43

_35:                                              ; preds = %_32
  br i1 %_17, label %_36, label %_43

_36:                                              ; preds = %_35
  %_37 = and i32 %_9, 61440
  %_38 = icmp eq i32 %_37, 16384
  %_39 = zext i1 %_38 to i64
  %_40 = and i32 %_8, 61440
  %_41 = icmp eq i32 %_40, 16384
  %.neg.i = sext i1 %_41 to i64
  %_42 = add nsw i64 %.neg.i, %_39
  br label %_43

_43:                                              ; preds = %_36, %_35, %_33, %_30, %_27, %_24
  %.0.i = phi i64 [ %_25, %_24 ], [ %_28, %_27 ], [ %_31, %_30 ], [ %_34, %_33 ], [ %_42, %_36 ], [ 0, %_35 ]
  %_44 = icmp eq i64 %.0.i, 2
  %spec.select9.v.i = select i1 %_19, i32 %_10, i32 %_11
  %spec.select9.i = sext i32 %spec.select9.v.i to i64
  %.2.i = select i1 %_44, i64 %spec.select9.i, i64 %.0.i
  %.not.i = icmp eq i64 %.2.i, 0
  br i1 %.not.i, label %_52, label %_45

_45:                                              ; preds = %_43
  %_46 = shl i64 %.2.i, 16
  %_47 = ashr i64 %_46, 32
  %.35.i = select i1 %_20, i64 %_47, i64 %.2.i
  %_48 = shl i64 %.35.i, 16
  %_49 = ashr i64 %_48, 32
  %.46.i = select i1 %_20, i64 %_47, i64 %_49
  %_50 = srem i64 %.46.i, 2
  %_51 = add nsw i64 %.46.i, %_50
  br label %_52

_52:                                              ; preds = %_45, %_43
  %.57.i = phi i64 [ %_51, %_45 ], [ %.2.i, %_43 ]
  %.not8.i = icmp eq i64 %.2.i, 2
  br i1 %.not8.i, label %_60, label %_53

_53:                                              ; preds = %_52
  %_54 = shl i64 %.2.i, 16
  %_55 = ashr i64 %_54, 32
  %.3.i = select i1 %_20, i64 %_55, i64 %.2.i
  %_56 = shl i64 %.3.i, 16
  %_57 = ashr i64 %_56, 32
  %.4.i = select i1 %_20, i64 %_55, i64 %_57
  %_58 = srem i64 %.4.i, 2
  %_59 = add nsw i64 %.4.i, %_58
  br label %_60

_60:                                              ; preds = %_53, %_52
  %.5.i = phi i64 [ %_59, %_53 ], [ %.2.i, %_52 ]
  %_61 = trunc i64 %.57.i to i32
  %_62 = sub nsw i32 0, %_61
  %_63 = trunc i64 %.57.i to i32
  %_64 = select i1 %_18, i32 %_62, i32 %_63
  %_65 = trunc i64 %.5.i to i32
  %_66 = sub nsw i32 0, %_65
  %_67 = trunc i64 %.5.i to i32
  %_68 = select i1 %_18, i32 %_66, i32 %_67
  %_69 = icmp eq i32 %_64, %_68
  call void @verifier.assume.not(i1 %_69)
  br label %_70

_70:                                              ; preds = %_60
  br label %verifier.error

verifier.error:                                   ; preds = %_70
  call void @seahorn.fail()
  br label %verifier.error.split

verifier.error.split:                             ; preds = %verifier.error
  ret i32 42
}

declare i1 @nondet.bool()

declare i64 @verifier.nondet.sym.bound()

declare void @shadow.mem.load(i32, i32, i8*)

declare void @shadow.mem.trsfr.load(i32, i32, i8*)

declare i32 @shadow.mem.store(i32, i32, i8*)

declare i32 @shadow.mem.global.init(i32, i32, i8*)

declare i32 @shadow.mem.init(i32, i8*)

declare i32 @shadow.mem.arg.init(i32, i8*)

declare void @shadow.mem.arg.ref(i32, i32, i32, i8*)

declare i32 @shadow.mem.arg.mod(i32, i32, i32, i8*)

declare i32 @shadow.mem.arg.new(i32, i32, i32, i8*)

declare void @shadow.mem.in(i32, i32, i32, i8*)

declare void @shadow.mem.out(i32, i32, i32, i8*)

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assert(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assert.not(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare i1 @sea.is_dereferenceable(i8* nocapture, i64) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @sea.assert.if(i1, i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @sea.synth.assume(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @sea.synth.assert(i1) #1

; Function Attrs: nofree norecurse nounwind readonly
declare i1 @sea.is_modified(i8* nocapture) #5

; Function Attrs: nofree norecurse nounwind
declare void @sea.reset_modified(i8* nocapture) #6

; Function Attrs: nofree norecurse nounwind readonly
declare i1 @sea.is_read(i8* nocapture) #5

; Function Attrs: nofree norecurse nounwind
declare void @sea.reset_read(i8* nocapture) #6

; Function Attrs: nofree norecurse nounwind readonly
declare i1 @sea.is_alloc(i8* nocapture) #5

; Function Attrs: nofree norecurse nounwind
declare void @sea.tracking_on() #6

; Function Attrs: nofree norecurse nounwind
declare void @sea.tracking_off() #6

; Function Attrs: nofree norecurse nounwind
declare void @sea.free(i8* nocapture) #6

; Function Attrs: nofree norecurse nounwind
declare void @sea.set_shadowmem(i8, i8* nocapture, i8) #6

; Function Attrs: nofree norecurse nounwind readonly
declare i8 @sea.get_shadowmem(i8, i8* nocapture) #5

attributes #0 = { "frame-pointer"="none" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #1 = { inaccessiblememonly nofree norecurse nounwind }
attributes #2 = { inaccessiblememonly nofree norecurse noreturn nounwind }
attributes #3 = { inaccessiblememonly }
attributes #4 = { nounwind uwtable "frame-pointer"="none" "min-legal-vector-width"="0" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #5 = { nofree norecurse nounwind readonly }
attributes #6 = { nofree norecurse nounwind }
attributes #7 = { nounwind }

!llvm.module.flags = !{!0, !1, !2, !3}
!llvm.ident = !{!4}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 7, !"PIC Level", i32 2}
!2 = !{i32 7, !"PIE Level", i32 2}
!3 = !{i32 7, !"uwtable", i32 1}
!4 = !{!"Ubuntu clang version 14.0.0-1ubuntu1"}
